import 'package:flutter/material.dart';
import 'package:feedfood/screen/myinfo.dart';
import 'package:feedfood/screen/home.dart';
import 'package:feedfood/screen/search.dart';
import 'package:feedfood/screen/diary.dart';


class mainscreen extends StatefulWidget {
  final int data;

  const mainscreen({Key? key, required this.data}) : super(key: key);

  @override
  State<mainscreen> createState() => _mainscreenstate();
}

class _mainscreenstate extends State<mainscreen> {
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
            icon: Icon(Icons.home_outlined),
            label: '홈',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.search),
            label: '검색',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.note_outlined),
            label: '미식일기',
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
        return Home();
      case 1:
        return Search();
      case 2:
        return Diary();
      case 3:
        return Myinfo();
      default:
        return Container();
    }
  }
}
