package com.muhammadsayed.design.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppChip(text: String, onClick: () -> Unit) {
    ElevatedAssistChip(
        shape = RoundedCornerShape(100.dp),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary
        ),
        colors = AssistChipDefaults.assistChipColors()
            .copy(containerColor = MaterialTheme.colorScheme.background),
        onClick = { onClick() },
        label = {
            Text(
                text = text,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Medium
            )
        },
        modifier = Modifier.padding(horizontal = 4.dp)
    )
}