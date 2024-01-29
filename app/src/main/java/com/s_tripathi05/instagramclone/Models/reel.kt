package com.s_tripathi05.instagramclone.Models

class reel {
    var reelUrl:String=""
    var caption:String=""
    var profileLink:String?=null
    constructor()
    constructor(reelUrl: String, caption: String) {
        this.reelUrl = reelUrl
        this.caption = caption
    }

    constructor(reelUrl: String, caption: String, profileLink: String) {
        this.reelUrl = reelUrl
        this.caption = caption
        this.profileLink = profileLink
    }

}