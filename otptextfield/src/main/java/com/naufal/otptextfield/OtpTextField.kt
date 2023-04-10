package com.naufal.otptextfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val neutral60 = Color(0xFF8E8E94)
val neutral100 = Color(0xFF1F1E2C)
val primaryRed = Color(0xFFE60013)
val primaryRedHover = Color(0xFFC00010)
val primaryRedFocus = Color(0xFFF2C4C8)

@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 6,
    isError: Boolean = false,
    textStyle: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    focusedBorderColor: Color = neutral100,
    unFocusedBorderColor: Color = neutral60,
    backgroundColor: Color = Color(0xFFFFFFFF),
    focusedTextColor: Color = neutral60,
    unFocusedTextColor: Color = neutral100,
    focusedBorderErrorColor: Color = primaryRed,
    unFocusedBorderErrorColor: Color = primaryRedHover,
    backgroundErrorColor: Color = primaryRedFocus,
    focusedTextErrorColor: Color = neutral60,
    unFocusedTextErrorColor: Color = neutral100,
    onOtpTextChange: (String, Boolean) -> Unit = { otp, isFilled -> },
) {
    BasicTextField(
        modifier = modifier,
        value = otpText,
        onValueChange = {
            if (it.length <= otpCount) {
                if (it.isEmpty()) {
                    onOtpTextChange.invoke(it, it.length == otpCount)
                } else if (it.last().isDigit()) {
                    onOtpTextChange.invoke(it, it.length == otpCount)
                }
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(otpCount) { index ->
                    if (isError) {
                        CharViewError(
                            index = index,
                            text = otpText,
                            textStyle = textStyle,
                            focusedBorderErrorColor = focusedBorderErrorColor,
                            unFocusedBorderErrorColor = unFocusedBorderErrorColor,
                            backgroundErrorColor = backgroundErrorColor,
                            focusedTextErrorColor = focusedTextErrorColor,
                            unFocusedTextErrorColor = unFocusedTextErrorColor,
                        )
                    } else {
                        CharView(
                            index = index,
                            text = otpText,
                            textStyle = textStyle,
                            focusedBorderColor = focusedBorderColor,
                            unFocusedBorderColor = unFocusedBorderColor,
                            backgroundColor = backgroundColor,
                            focusedTextColor = focusedTextColor,
                            unFocusedTextColor = unFocusedTextColor,
                        )
                    }
                    if (index != otpCount - 1) {
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }
        }
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String,
    textStyle: TextStyle,
    focusedBorderColor: Color,
    unFocusedBorderColor: Color,
    backgroundColor: Color,
    focusedTextColor: Color,
    unFocusedTextColor: Color,
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> ""
        index > text.length -> ""
        else -> text[index].toString()
    }
    Text(
        modifier = Modifier
            .defaultMinSize(minWidth = 40.dp)
            .border(
                1.dp, when {
                    isFocused -> focusedBorderColor
                    else -> unFocusedBorderColor
                }, RoundedCornerShape(4.dp)
            )
            .background(color = backgroundColor)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        text = char,
        style = textStyle,
        color = if (isFocused) {
            focusedTextColor
        } else {
            unFocusedTextColor
        },
        textAlign = TextAlign.Center
    )
}

@Composable
private fun CharViewError(
    index: Int,
    text: String,
    textStyle: TextStyle,
    focusedBorderErrorColor: Color,
    unFocusedBorderErrorColor: Color,
    backgroundErrorColor: Color,
    focusedTextErrorColor: Color,
    unFocusedTextErrorColor: Color,
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> ""
        index > text.length -> ""
        else -> text[index].toString()
    }
    Text(
        modifier = Modifier
            .defaultMinSize(minWidth = 40.dp)
            .border(
                1.dp, when {
                    isFocused -> focusedBorderErrorColor
                    else -> unFocusedBorderErrorColor
                }, RoundedCornerShape(4.dp)
            )
            .background(color = backgroundErrorColor)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        text = char,
        style = textStyle,
        color = if (isFocused) {
            focusedTextErrorColor
        } else {
            unFocusedTextErrorColor
        },
        textAlign = TextAlign.Center
    )
}

@Composable
fun Center(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content
    )
}

@Preview
@Composable
fun OtpPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        var otp by rememberSaveable { mutableStateOf("") }
        val onOtpChange: (String, Boolean) -> Unit = { value, isFilled ->
            otp = value
        }

        Center {
            OtpTextField(
                otpText = otp,
                onOtpTextChange = onOtpChange,
                isError = false
            )
        }
    }
}