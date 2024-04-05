import 'package:flutter/material.dart';
import 'package:vtt_app/screen/mainscreen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: mainscreen(data: 0),
    );
  }
}
