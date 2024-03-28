import 'package:flutter/material.dart';
import 'package:feedfood/const/colors.dart';
import 'package:flutter/services.dart';

class CustomTextField extends StatelessWidget {
  final String label;
  final bool isTime; // 시간 입력용인지 묻는용
  final TextEditingController? controller;

  const CustomTextField({
    required this.label,
    required this.isTime,
    this.controller,
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          label,
          style: TextStyle(
            color: PRIMARY_COLOR,
            fontWeight: FontWeight.w600,
          ),
        ),
        Expanded(
          flex: isTime ? 0 : 1,
          child: TextFormField(
            controller: controller,
            cursorColor: Colors.grey,
            maxLines: isTime ? 1 : null,
            expands: !isTime,
            keyboardType: isTime ? TextInputType.number : TextInputType.multiline,
            inputFormatters: isTime
                ? [
              FilteringTextInputFormatter.digitsOnly,
            ]
                : [],
            decoration: InputDecoration(
              border: InputBorder.none,
              filled: true,
              fillColor: Colors.grey[300],
              suffixText: isTime ? '시' : null,
            ),
          ),
        ),
      ],
    );
  }
}
