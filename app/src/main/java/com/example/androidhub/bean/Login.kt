package com.example.androidhub.bean

data class Login(
    var loginType: Int,// 1
    var code: Int,// 200
    var account: Account,
    var token: String,// a48f5e18ab2124a4743ff3a6a2f6bbb4f0ef426550e3faf1e8ab3f02e3e9593017e7abc5c056114f6867ea0ad33760e041049cea1c6bb9b6
    var profile: Profile,
    var bindings: List<Binding>
)

data class Profile(
    var mutual: Boolean,// false
    var remarkName: String,
    var expertTags: String,
    var authStatus: Int,// 0
    var defaultAvatar: Boolean,// false
    var avatarUrl: String,// https://p4.music.126.net/Fw0nhFcCpFXpMMJpuRv7_g==/109951163926206834.jpg
    var djStatus: Int,// 0
    var experts: Experts,
    var detailDescription: String,//
    var followed: Boolean,// false
    var backgroundUrl: String,// https://p3.music.126.net/pXSB1TFV7cF2GDebLYxaKA==/109951163907173311.jpg
    var description: String,//
    var backgroundImgIdStr: String,// 109951163907173311
    var avatarImgIdStr: String,// 109951163926206834
    var userId: Int,// 395567687
    var vipType: Int,// 11
    var gender: Int,// 1
    var accountStatus: Int,// 0
    var birthday: Long,// 4133867882617
    var avatarImgId: Long,// 109951163926206830
    var nickname: String,// zhangwww99
    var city: Int,// 1010000
    var userType: Int,// 0
    var backgroundImgId: Long,// 109951163907173310
    var province: Int,// 1000000
    var signature: String,//
    var authority: Int,// 0
    var avatarImgId_str: String,// 109951163926206834
    var followeds: Int,// 3
    var follows: Int,// 0
    var eventCount: Int,// 0
    var playlistCount: Int,// 24
    var playlistBeSubscribedCount: Int// 0
)

data class Account(
    var id: Int,// 395567687
    var userName: String,// 1_13997855416
    var type: Int,// 1
    var status: Int,// 0
    var whitelistAuthority: Int,// 0
    var createTime: Long,// 1483762008502
    var salt: String,// [B@78e84e7a
    var tokenVersion: Int,// 0
    var ban: Int,// 0
    var baoyueVersion: Int,// 1
    var donateVersion: Int,// 0
    var vipType: Int,// 11
    var viptypeVersion: Long,// 1579143717937
    var anonimousUser: Boolean// false
)

class Experts

data class Binding(
    var refreshTime: Int,// 1519721200
    var expiresIn: Int,// 2147483647
    var bindingTime: Long,// 1519721200252
    var expired: Boolean,// false
    var url: String,//
    var userId: Int,// 395567687
    var tokenJsonStr: String,// {"countrycode":"","cellphone":"13997855416","hasPassword":true}
    var id: Long,// 6548873230
    var type: Int// 1
)