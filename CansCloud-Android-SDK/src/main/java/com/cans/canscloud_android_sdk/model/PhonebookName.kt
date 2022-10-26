package com.cans.canscloud_android_sdk.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Name", strict = false)
data class PhonebookName @JvmOverloads constructor(
    @field:Element(name = "First", required = false)
    @param:Element(name = "First", required = false)
    var first: String? = null,
)
