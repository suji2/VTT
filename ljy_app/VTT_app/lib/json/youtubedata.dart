class YoutubeData {
  final String id;
  final String url;
  final String title;
  final String description;
  final String channel;
  final String date;
  final String thumbnail;

  YoutubeData({
    required this.id,
    required this.url,
    this.title = '',
    this.description = '',
    this.channel = '',
    this.date = '',
    this.thumbnail = '',
  });

  factory YoutubeData.fromJson(Map<String, dynamic> json) {
    return YoutubeData(
      id: json['id'] ?? '',
      url: json['url'] ?? '',
      title: json['title'] ?? '',
      description: json['description'] ?? '',
      channel: json['channel'] ?? '',
      date: json['date'] ?? '',
      thumbnail: json['thumbnail'] ?? '',
    );
  }
}
