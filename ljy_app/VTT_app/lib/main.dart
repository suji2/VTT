import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:vtt_app/const/googleUser.dart';
import 'package:vtt_app/screen/mainscreen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // MultiProvider로 여러 Provider를 함께 제공
    return MultiProvider(
      providers: [
        // GoogleUser를 제공하는 Provider
        Provider<GoogleUser>(
          create: (_) => GoogleUser(),
        ),
        // 다른 Provider가 필요한 경우 여기에 추가
      ],
      child: MaterialApp(
        home: Mainscreen(data: 0),
      ),
    );
  }
}
