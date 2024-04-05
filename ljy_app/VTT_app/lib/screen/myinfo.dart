import 'package:flutter/material.dart';
import 'package:google_sign_in/google_sign_in.dart';
import 'package:googleapis/youtube/v3.dart' as youtube;
import 'package:http/http.dart' as http;

class LoginScreen extends StatefulWidget {
  @override
  _LoginScreenState createState() => _LoginScreenState();
}
const List<String> scopes = <String>[
  'email',
  'https://www.googleapis.com/auth/youtube',
];

GoogleSignIn _googleSignIn = GoogleSignIn(
  clientId: "935624103884-n0o0a7ejgbko6i9u9m3m0mv2nvo6f6to.apps.googleusercontent.com",
  scopes: scopes,
);

class _LoginScreenState extends State<LoginScreen> {



  GoogleSignInAccount? _currentUser;

  Future<void> _handleSignIn() async {
    try {
      await _googleSignIn.signIn();
    } catch (error) {
      print(error);
    }
  }
  Future<void> _handleSignOut() async {
    try {
      await _googleSignIn.signOut();
    } catch (error) {
      print(error);
    }
  }



  @override
  Widget build(BuildContext context) {
    if (_currentUser != null) {
      return Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Text('Signed in as: ${_currentUser!.displayName}'),
          Text('Email: ${_currentUser!.email}'),
          ElevatedButton(
            child: Text('Fetch YouTube Data'),
            onPressed: (){},
          ),
          ElevatedButton(
            child: Text('Sign out'),
            onPressed: (){_handleSignOut();},
          ),
        ],
      );
    } else {
      return Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Text('You are not signed in.'),
          ElevatedButton(
            child: Text('Sign in with Google'),
            onPressed: (){_handleSignIn();},
          ),
        ],
      );
    }
  }
}
