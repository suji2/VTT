import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:codenamea/screen/mainscreen.dart';
import 'package:pull_to_refresh/pull_to_refresh.dart';

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {
  List<String> items = ["1", "2", "3", "4", "5", "6", "7", "8"];
  RefreshController _refreshController =
  RefreshController(initialRefresh: false);
  //새로고침
  void _onRefresh() async{
    await Future.delayed(Duration(milliseconds: 1000));

    _refreshController.refreshCompleted();
  }

  //무한 스크롤
  void _onLoading() async{
    await Future.delayed(Duration(milliseconds: 1000));
    setState(() {
      items.add((items.length+1).toString());
    });
    _refreshController.loadComplete();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.white,
        title: Image.asset(
          'assets/img/foodfeed_upicon.jpg',
          width: 100,
        ),
        actions: [
          IconButton(
            icon: Icon(Icons.search_outlined),
            onPressed: () {
              Navigator.pushReplacement(
                context,
                PageRouteBuilder(
                  pageBuilder: (context, animation, secondaryAnimation) {
                    return mainscreen(data: 1); //
                  },
                ),
              );
            },
            iconSize: 28,
          ),
        ],
        elevation: 0,
        iconTheme: IconThemeData(color: Colors.black),
      ),
      body: SmartRefresher(
        enablePullDown: true,
        enablePullUp: true,
        header: CustomHeader(
          builder: (BuildContext context, RefreshStatus? mode){
            Widget body;
            if (mode == RefreshStatus.idle) {
              body = Text('hi');
            } else if (mode == RefreshStatus.refreshing) {
              body = CupertinoActivityIndicator();
            } else {
              body = Text('body');
            }
            return Container(
              height: 600,
              child: Center(child: body,
              ),
            );
          },
        ),
          controller: _refreshController,
          onRefresh: _onRefresh,
          onLoading: _onLoading,
          child: ListView.builder(
            itemBuilder: (c, i) => Container(
              height: 500,
              color: Colors.white,
              padding: EdgeInsets.all(8.0),
              child: Row(
                children: [
                  Container(
                    width: 200.0,
                    height: 200.0,
                    decoration: BoxDecoration(
                      shape: BoxShape.rectangle,
                      image: DecorationImage(
                        fit: BoxFit.cover,
                        image: AssetImage('assets/img/foodfeed_icon.jpg'),
                      ),
                    ),
                  ),
                  SizedBox(width: 16.0),
                  Expanded(
                    child: Text(
                      "유튜브 ${items[i % items.length]}",
                      style: TextStyle(
                        fontSize: 18.0,
                        fontWeight: FontWeight.bold,
                        color: Colors.blue,
                      ),
                    ),
                  ),
                ],
              ),
            ),
            itemExtent: 200.0,
            itemCount: items.length,
          ),
      ),
    );
  }
}
