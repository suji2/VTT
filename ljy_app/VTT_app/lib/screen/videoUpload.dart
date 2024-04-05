import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class VideoUp extends StatefulWidget{
  const VideoUp({super.key});
  @override
  State<VideoUp> createState() => _VideoUp();
}


class _VideoUp extends State<VideoUp>{

  @override
  Widget build(BuildContext context){
    return Scaffold(
      body: Text("VideoUp"),
    );
  }
}