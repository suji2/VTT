import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:feedfood/component/custom_text_field.dart';
import 'package:flutter/material.dart';
import 'package:feedfood/const/colors.dart';
import 'package:uuid/uuid.dart'; // uuid 패키지를 추가

class ScheduleBottomSheet extends StatefulWidget {
  const ScheduleBottomSheet({Key? key}) : super(key: key);

  @override
  State<ScheduleBottomSheet> createState() => _ScheduleBottomSheetState();
}

class _ScheduleBottomSheetState extends State<ScheduleBottomSheet> {
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;
  late TextEditingController startTimeController;
  late TextEditingController endTimeController;
  late TextEditingController contentController;
  final Uuid uuid = Uuid(); // UUID 생성기

  @override
  void initState() {
    super.initState();
    startTimeController = TextEditingController();
    endTimeController = TextEditingController();
    contentController = TextEditingController();
  }

  @override
  void dispose() {
    startTimeController.dispose();
    endTimeController.dispose();
    contentController.dispose();
    super.dispose();
  }

  Widget build(BuildContext context) {
    final bottomInset = MediaQuery.of(context).viewInsets.bottom;

    return SafeArea(
      child: Container(
        height: MediaQuery.of(context).size.height / 2 + bottomInset,
        color: Colors.white,
        child: Padding(
          padding: EdgeInsets.only(left: 8, right: 8, top: 8, bottom: bottomInset),
          child: Column(
            children: [
              Row(
                children: [
                  Expanded(
                    child: CustomTextField(
                      label: '시작시간',
                      isTime: true,
                      controller: startTimeController,
                    ),
                  ),
                  const SizedBox(width: 16.0),
                  Expanded(
                    child: CustomTextField(
                      label: '종료시간',
                      isTime: true,
                      controller: endTimeController,
                    ),
                  ),
                ],
              ),
              const SizedBox(height: 8.0),
              Expanded(
                child: CustomTextField(
                  label: '먹은음식',
                  isTime: false,
                  controller: contentController,
                ),
              ),
              SizedBox(
                width: double.infinity,
                child: ElevatedButton(
                  onPressed: onSavePressed,
                  style: ElevatedButton.styleFrom(
                    primary: PRIMARY_COLOR,
                  ),
                  child: Text('저장'),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  void onSavePressed() async {
    try {

      String uuidValue = uuid.v4();


      await _firestore.collection('schedules').doc(uuidValue).set({
        'id': uuidValue,
        'startTime': startTimeController.text,
        'endTime': endTimeController.text,
        'content': contentController.text,
      });

      // 성공적으로 저장되면 필드 초기화
      startTimeController.clear();
      endTimeController.clear();
      contentController.clear();

    } catch (e) {
      print('데이터 저장 중 오류 발생: $e');

    }
  }
}
