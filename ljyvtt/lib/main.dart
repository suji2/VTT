import 'package:flutter/material.dart';
import 'package:ljyvtt/screen/mainscreen.dart';


void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // MultiProvider로 여러 Provider를 함께 제공
    return MaterialApp(
        home: Mainscreen(data: 0),
      );
  }
}
