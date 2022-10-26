package com.cans.canscloud_android_sdk.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Phone", strict = false)
data class PhonebookPhone @JvmOverloads constructor(
    @field:Element(name = "Phone", required = false)
    @param:Element(name = "Phone", required = false)
    var phone: String? = null,
)
