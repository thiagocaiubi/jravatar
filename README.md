# Jravatar

Jravatar is a fluent Java library for accessing avatar images from gravatar.com

## Usage example

### Get the image URL (regular access > http://www.gravatar.com/....)

	String url = new Jravatar()
		.withSize(50)
		.withRating(GravatarRating.GENERAL_AUDIENCES)
		.withDefaultImage(GravatarDefaultImage.IDENTICON)
		.getUrl("iHaveAn@email.com");
		
### Get the image URL (secure access > https://secure.gravatar.com/........)
	String url = new Jravatar()
		.withSecure()
		.withSize(50)
		.withRating(GravatarRating.GENERAL_AUDIENCES)
		.withDefaultImage(GravatarDefaultImage.IDENTICON)
		.getUrl("iHaveAn@email.com");

### Get the image URL, force default gravatar image

# Either regular or secure access, it builds the url with all the parameters. Gravatar will ignore all parameters and respond the default image.

	String url = new Jravatar()
		.withSecure()
		.withSize(50)
		.withRating(GravatarRating.GENERAL_AUDIENCES)
		.withDefaultImage(GravatarDefaultImage.IDENTICON)
		.forceDefault()
		.getUrl("iHaveAn@email.com");

### Download image

	byte[] jpg = new Jravatar()
		.withSize(50)
		.withRating(GravatarRating.GENERAL_AUDIENCES)
		.withDefaultImage(GravatarDefaultImage.IDENTICON)
		.download("iHaveAn@email.com");