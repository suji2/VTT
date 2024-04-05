import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class Youtubelist extends StatefulWidget{
  const Youtubelist({super.key});
  @override
  State<Youtubelist> createState() => _YoutubelistState();
}
class _YoutubelistState extends State<Youtubelist>{

  @override
  Widget build(BuildContext context){
    return Scaffold(
      body: Text("youtubelist"),
    );
  }
}