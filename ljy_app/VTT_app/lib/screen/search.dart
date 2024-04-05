import 'package:flutter/material.dart';
import 'package:vtt_app/screen/youtubelist.dart';

class Search extends StatefulWidget {
  const Search({Key? key}) : super(key: key);

  @override
  State<Search> createState() => _SearchState();
}

class _SearchState extends State<Search> {
  TextEditingController _searchController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.white,
        title: TextField(
          controller: _searchController,
          autofocus: true,
          decoration: InputDecoration(
            hintText: '검색해주세요',
            border: InputBorder.none,
          ),
          onSubmitted: (value) {

            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => Youtubelist()),
            );
          },
        ),
      ),
      body: Column(
        children: [
          SizedBox(height: 20),
          Center(
            child: OutlinedButton.icon(
              onPressed: () {

                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => Youtubelist()),
                );
              },
              icon: Icon(Icons.youtube_searched_for_outlined),
              label: Text(
                "내주변 검색",
                style: TextStyle(
                  color: Colors.black,
                ),
              ),
              style: ButtonStyle(
                foregroundColor: MaterialStateProperty.all<Color>(Colors.black),
                backgroundColor: MaterialStateProperty.all<Color>(Colors.grey[200]!),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
