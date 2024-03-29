import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:intl/date_symbol_data_local.dart';
import 'package:codenamea/screen/mainscreen.dart';
import 'package:get_it/get_it.dart';
import 'package:flutter_naver_map/flutter_naver_map.dart';



void main() async {
  WidgetsFlutterBinding.ensureInitialized(); // Flutter 엔진 초기화

  await initializeDateFormatting('ko_KR', null);

  await NaverMapSdk.instance.initialize(clientId: 'ttznnozw7u');


  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: mainscreen(data: 0),
    );
  }
}
