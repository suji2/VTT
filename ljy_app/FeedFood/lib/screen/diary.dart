import 'dart:math';
import 'package:flutter/material.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:feedfood/component/main_calendar.dart';
import 'package:feedfood/component/schedule_card.dart';
import 'package:feedfood/component/today_banner.dart';
import 'package:feedfood/component/schedule_bottom_sheet.dart';
import 'package:feedfood/const/colors.dart';

class Diary extends StatefulWidget {
  const Diary({Key? key}) : super(key: key);

  @override
  State<Diary> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<Diary> {
  DateTime selectedDate = DateTime.utc(
    DateTime.now().year,
    DateTime.now().month,
    DateTime.now().day,
  );

  List<String> foods = [];

  @override
  void initState() {
    super.initState();
    // Firebase에서 데이터 가져와서 foods 리스트에 저장
    fetchFoodsFromFirebase();
  }

  Future<void> fetchFoodsFromFirebase() async {
    try {
      // Firebase에서 'schedules' 컬렉션의 모든 문서 가져오기
      QuerySnapshot querySnapshot = await FirebaseFirestore.instance.collection('schedules').get();

      // 'content' 필드를 foods 리스트에 추가
      querySnapshot.docs.forEach((DocumentSnapshot document) {
        String content = document['content'] as String;
        foods.add(content);
      });
      
      setState(() {});
    } catch (e) {
      print('파이어베이스 에러: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      floatingActionButton: Column(
        mainAxisAlignment: MainAxisAlignment.end,
        children: [
          FloatingActionButton(
            backgroundColor: PRIMARY_COLOR,
            onPressed: () {
              showModalBottomSheet(
                context: context,
                isDismissible: true,
                isScrollControlled: true,
                builder: (_) => ScheduleBottomSheet(),
              );
            },
            child: Icon(Icons.add),
          ),
          SizedBox(height: 16.0),
          FloatingActionButton(
            backgroundColor: PRIMARY_COLOR,
            onPressed: () {
              // Star 버튼이 눌렸을 때 음식 추천
              showFoodRecommendation();
            },
            child: Icon(Icons.star),
          ),
        ],
      ),
      body: SafeArea(
        child: Column(
          children: [
            MainCalendar(
              selectedDate: selectedDate,
              onDaySelected: onDaySelected,
            ),
            SizedBox(height: 8.0),
            TodayBanner(count: 0, selectedDate: selectedDate),
            SizedBox(height: 8.0),
            Expanded(
              child: SingleChildScrollView(
                child: StreamBuilder(
                  stream: FirebaseFirestore.instance
                      .collection('schedules')
                      .snapshots(),
                  builder: (context, AsyncSnapshot<QuerySnapshot> snapshot) {
                    if (snapshot.connectionState == ConnectionState.waiting) {
                      return CircularProgressIndicator();
                    } else if (snapshot.hasError) {
                      return Text('Error: ${snapshot.error}');
                    } else {
                      List<QueryDocumentSnapshot> documents = snapshot.data!.docs;

                      return Column(
                        children: documents.map((document) {
                          int? startTime = int.tryParse(document['startTime'] as String? ?? '');
                          int? endTime = int.tryParse(document['endTime'] as String? ?? '');
                          String content = document['content'] as String;

                          return ScheduleCard(
                            startTime: startTime ?? 0,
                            endTime: endTime ?? 0,
                            content: content,
                          );
                        }).toList(),
                      );
                    }
                  },
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  void onDaySelected(DateTime selectedDate, DateTime focusedDate) {
    setState(() {
      this.selectedDate = selectedDate;
    });
  }

  void showFoodRecommendation() {

    String recommendedFood = recommendFoodBasedOnContent();

    // 음식 추천 다이얼로그 표시
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('음식 추천'),
          content: Text('오늘의 추천 음식: $recommendedFood'),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: Text('확인'),
            ),
          ],
        );
      },
    );
  }

  String recommendFoodBasedOnContent() {

    if (foods.isNotEmpty) {
      // 각 음식에 대한 가중치 설정
      Map<String, double> foodWeights = {};
      for (String food in foods) {

        int occurrenceCount = foods.where((element) => element == food).length;
        double weight = 1.0 + (occurrenceCount * 0.1);
        foodWeights[food] = weight;
      }

      // 가중치를 고려하여 랜덤으로 음식 선택
      String selectedFood = _weightedRandom(foodWeights);
      return selectedFood;
    } else {
      return '추천할 음식이 없습니다.';
    }
  }

  String _weightedRandom(Map<String, double> weights) {
    // 가중치를 고려하여 랜덤으로 키 선택
    double totalWeight = weights.values.reduce((value, element) => value + element);
    double randomValue = Random().nextDouble() * totalWeight;

    double accumulator = 0.0;
    for (MapEntry<String, double> entry in weights.entries) {
      accumulator += entry.value;
      if (randomValue < accumulator) {
        return entry.key;
      }
    }

    // 이 부분은 일반적으로 실행되지 않겠지만, 예외 처리를 위해 추가
    return weights.keys.first;
  }
}
