import 'package:flutter/material.dart';
import 'package:googleapis/youtube/v3.dart';
import 'package:vtt_app/json/youtubedata.dart';
import 'package:vtt_app/screen/myfav.dart';
import 'package:vtt_app/screen/myinfo.dart';
import 'package:vtt_app/screen/search.dart';
import 'package:vtt_app/screen/videoUpload.dart';
import 'package:vtt_app/const/googleUser.dart';
import 'package:provider/provider.dart';

class Mainscreen extends StatefulWidget {
  final int data;
  const Mainscreen({Key? key, required this.data}) : super(key: key);

  @override
  State<Mainscreen> createState() => _mainscreenstate();
}

class _mainscreenstate extends State<Mainscreen> {
  int _selectedIndex = 0;

  @override
  void initState() {
    super.initState();

    if (widget.data == 1) {
      setState(() {
        _selectedIndex = 1;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    //final googleUser = Provider.of<GoogleUser>(context);

    return Scaffold(
      body: Center(
        child: _getPage(_selectedIndex),
      ),
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _selectedIndex,
        unselectedItemColor: Colors.grey,
        selectedItemColor: Colors.black,
        onTap: _onItemTapped,
        items: [
          BottomNavigationBarItem(
            icon: Icon(Icons.menu_outlined),
            label: '내목록',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.search),
            label: '검색',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.video_call_outlined),
            label: '영상업로드',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.account_circle_outlined),
            label: '내정보',
          ),
        ],
      ),
    );
  }

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  Widget _getPage(int index) {
    switch (index) {
      case 0:
        return Myfav();
      case 1:
        return Search();
      case 2:
        return VideoUp();
      case 3:
        return LoginScreen();
      default:
        return Container();
    }
  }
}
