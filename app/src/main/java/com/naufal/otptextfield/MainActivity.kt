package com.naufal.otptextfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.naufal.otptextfield.ui.theme.OtpTextFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtpTextFieldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
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
                                isError = false,
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                ),
                                focusedBorderColor = neutral100,
                                unFocusedBorderColor = neutral60,
                                backgroundColor = Color(0xFFFFFFFF),
                                focusedTextColor = neutral60,
                                unFocusedTextColor = neutral100,
                                focusedBorderErrorColor = primaryRed,
                                unFocusedBorderErrorColor = primaryRedHover,
                                backgroundErrorColor = primaryRedFocus,
                                focusedTextErrorColor = neutral60,
                                unFocusedTextErrorColor = neutral100,
                            )
                        }
                    }
                }
            }
        }
    }
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OtpTextFieldTheme {
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
}