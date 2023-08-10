package com.livetv

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.AppUtils.parseJson
import com.lagradost.cloudstream3.utils.AppUtils.toJson
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.getQualityFromName
import com.phimhd.AppController
import com.phimhd.IPTV
import com.phimhd.ListIPTV
import com.phimhd.ResponseIPTV

class LiveTVProvider : MainAPI() {
    override var name = IPTV_API_NAME
    override var mainUrl = "https://ophimx.app/api/phimhd/iptv"
    override val hasMainPage = true
    override val hasDownloadSupport = false
    override val hasChromecastSupport: Boolean
        get() = true
    override val supportedTypes: Set<TvType>
        get() = setOf(
            TvType.Live,
        )
    companion object{
        const val IPTV_API_NAME = "Bóng Đá & Tivi Online"

    }
   
   

    override suspend fun load(url: String): LoadResponse? {
        return parseJson<LiveStreamLoadResponse>(url)
    }

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        callback.invoke(ExtractorLink(source = data,name= IPTV_API_NAME, url = data, referer = "", isM3u8 = true,quality = getQualityFromName("720P")))
        return true
    }
}