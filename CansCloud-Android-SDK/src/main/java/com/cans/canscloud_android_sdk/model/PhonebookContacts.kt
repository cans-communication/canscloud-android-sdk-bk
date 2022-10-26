package com.cans.canscloud_android_sdk.model

import androidx.annotation.Keep
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "Contacts", strict = false)
data class PhonebookContacts @JvmOverloads constructor(
    @field:ElementList(name = "Contact", inline = true)
    @param:ElementList(name = "Contact", inline = true)
    var contact: List<PhonebookContact>? = null
)
