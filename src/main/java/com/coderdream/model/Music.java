package com.coderdream.model;

/**
 * <pre>
 * 音乐model 
 * 1）参数Title：标题，本例中可以设置为歌曲名称；
 * 
 * 2）参数Description：描述，本例中可以设置为歌曲的演唱者；
 * 
 * 3）参数MusicUrl：普通品质的音乐链接；
 * 
 * 4）参数HQMusicUrl：高品质的音乐链接，在WIFI环境下会优先使用
 * 该链接播放音乐；
 * 
 * 5）参数ThumbMediaId：缩略图的媒体ID，通过上传多媒体文件获得；
 * 它指向的是一张图片，最终会作为音乐消息左侧绿色方形区域的背景图片。
 * 
 * 上述5个参数中，最为重要的是MusicUrl和HQMusicUrl，这也是本文
 * 的重点，如何根据歌曲名称获得歌曲的链接。如果读者只能得到歌曲的一个
 * 链接，可以将MusicUrl和HQMusicUrl设置成一样的。至于
 * ThumbMediaId参数，必须是通过微信认证的服务号才能得到，
 * 普通的服务号与订阅号可以忽略该参数，也就是说，
 * 在回复给微信服务器的XML中可以不包含ThumbMediaId参数。
 * （如果没有通过认证，就不要加入这个参数！！！）
 * 
 * <pre>
 */
public class Music {
	/**
	 * 音乐标题
	 */
	private String Title;
	/**
	 * 音乐描述
	 */
	private String Description;
	/**
	 * 音乐链接
	 */
	private String MusicUrl;
	/**
	 * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	 */
	private String HQMusicUrl;

	// 缩略图的媒体id，通过上传多媒体文件得到的id
	// private String ThumbMediaId;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}

	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String musicUrl) {
		HQMusicUrl = musicUrl;
	}

	// public String getThumbMediaId() {
	// return ThumbMediaId;
	// }
	//
	// public void setThumbMediaId(String thumbMediaId) {
	// ThumbMediaId = thumbMediaId;
	// }
}
