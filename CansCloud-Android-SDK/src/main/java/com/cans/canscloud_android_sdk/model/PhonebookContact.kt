package com.cans.canscloud_android_sdk.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "Contact", strict = false)
data class PhonebookContact @JvmOverloads constructor(
    @field:Attribute(name = "id", required = false)
    @param:Attribute(name = "id", required = false)
    var id: String? = null,

    @field:Element(name = "Name", required = false)
    @param:Element(name = "Name", required = false)
    var name: PhonebookName? = null,

    @field:ElementList(name = "Phone", inline = true)
    @param:ElementList(name = "Phone", inline = true)
    var phone: List<PhonebookPhone>? = null
)
