create table countries(
	id integer auto_increment,
	name varchar(255)
);

create table city_weather(
	id integer auto_increment,
	city_name varchar(255), 
	weather_response varchar(1000),
	last_request timestamp default current_timestamp
)