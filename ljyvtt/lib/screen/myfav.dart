import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class Myfav extends StatefulWidget {
  const Myfav({Key? key}) : super(key: key);

  @override
  State<Myfav> createState() => _Myfav();
}

class _Myfav extends State<Myfav> {

  @override
  Widget build(BuildContext context){
    return Scaffold(
      body: Text("Myinfo"),
    );
  }

}
