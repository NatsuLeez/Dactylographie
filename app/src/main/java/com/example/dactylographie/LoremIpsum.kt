package com.example.dactylographie

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

public class LoremIpsum: PreviewParameterProvider<LoremIpsum> {
    override val values: Sequence<LoremIpsum>
        get() = sequenceOf(
            LoremIpsum()
        )
}