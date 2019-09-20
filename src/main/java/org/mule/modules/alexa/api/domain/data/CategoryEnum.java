package org.mule.modules.alexa.api.domain.data;

public enum CategoryEnum {
	ALARMS_AND_CLOCKS("ALARMS_AND_CLOCKS"),ASTROLOGY("ASTROLOGY"),BUSINESS_AND_FINANCE("BUSINESS_AND_FINANCE"),CALCULATORS("CALCULATORS"),CALENDARS_AND_REMINDERS("CALENDARS_AND_REMINDERS"),CHILDRENS_EDUCATION_AND_REFERENCE("CHILDRENS_EDUCATION_AND_REFERENCE"),
	CHILDRENS_GAMES("CHILDRENS_GAMES"),CHILDRENS_MUSIC_AND_AUDIO("CHILDRENS_MUSIC_AND_AUDIO"),CHILDRENS_NOVELTY_AND_HUMOR("CHILDRENS_NOVELTY_AND_HUMOR"),COMMUNICATION("COMMUNICATION"),
	CONNECTED_CAR("CONNECTED_CAR"),COOKING_AND_RECIPE("COOKING_AND_RECIPE"),
	CURRENCY_GUIDES_AND_CONVERTERS("CURRENCY_GUIDES_AND_CONVERTERS"),DATING("DATING"),DELIVERY_AND_TAKEOUT("DELIVERY_AND_TAKEOUT"),DEVICE_TRACKING("DEVICE_TRACKING"),
	EDUCATION_AND_REFERENCE("EDUCATION_AND_REFERENCE"),EVENT_FINDERS("EVENT_FINDERS"),EXERCISE_AND_WORKOUT("EXERCISE_AND_WORKOUT"),FASHION_AND_STYLE("FASHION_AND_STYLE"),FLIGHT_FINDERS("FLIGHT_FINDERS"),FRIENDS_AND_FAMILY("FRIENDS_AND_FAMILY"),
	GAME_INFO_AND_ACCESSORY("GAME_INFO_AND_ACCESSORY"),GAMES("GAMES"),HEALTH_AND_FITNESS("HEALTH_AND_FITNESS"),HOTEL_FINDERS("HOTEL_FINDERS"),KNOWLEDGE_AND_TRIVIA("KNOWLEDGE_AND_TRIVIA"),
	MOVIE_AND_TV_KNOWLEDGE_AND_TRIVIA("MOVIE_AND_TV_KNOWLEDGE_AND_TRIVIA"),MOVIE_INFO_AND_REVIEWS("MOVIE_INFO_AND_REVIEWS"),MOVIE_SHOWTIMES("MOVIE_SHOWTIMES"),MUSIC_AND_AUDIO_ACCESSORIES("MUSIC_AND_AUDIO_ACCESSORIES"),
	MUSIC_AND_AUDIO_KNOWLEDGE_AND_TRIVIA("MUSIC_AND_AUDIO_KNOWLEDGE_AND_TRIVIA"),MUSIC_INFO_REVIEWS_AND_RECOGNITION_SERVICE("MUSIC_INFO_REVIEWS_AND_RECOGNITION_SERVICE"),
	NAVIGATION_AND_TRIP_PLANNER("NAVIGATION_AND_TRIP_PLANNER"),NEWS("NEWS"),NOVELTY("NOVELTY"),ORGANIZERS_AND_ASSISTANTS("ORGANIZERS_AND_ASSISTANTS"),PETS_AND_ANIMAL("PETS_AND_ANIMAL"),PODCAST("PODCAST"),PUBLIC_TRANSPORTATION("PUBLIC_TRANSPORTATION"),RELIGION_AND_SPIRITUALITY("RELIGION_AND_SPIRITUALITY"),RESTAURANT_BOOKING_INFO_AND_REVIEW("RESTAURANT_BOOKING_INFO_AND_REVIEW"),
	SCHOOLS("SCHOOLS"),SCORE_KEEPING("SCORE_KEEPING"),SELF_IMPROVEMENT("SELF_IMPROVEMENT"),SHOPPING("SHOPPING"),SMART_HOME("SMART_HOME"),SOCIAL_NETWORKING("SOCIAL_NETWORKING"),SPORTS_GAMES("SPORTS_GAMES"),
	SPORTS_NEWS("SPORTS_NEWS"),STREAMING_SERVICE("STREAMING_SERVICE"),TAXI_AND_RIDESHARING("TAXI_AND_RIDESHARING"),TO_DO_LISTS_AND_NOTES("TO_DO_LISTS_AND_NOTES"),
	TRANSLATORS("TRANSLATORS"),TV_GUIDES("TV_GUIDES"),UNIT_CONVERTERS("UNIT_CONVERTERS"),WEATHER("WEATHER"),WINE_AND_BEVERAGE("WINE_AND_BEVERAGE"),ZIP_CODE_LOOKUP("ZIP_CODE_LOOKUP");

	private String value;
	CategoryEnum(String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
