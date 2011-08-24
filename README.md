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

### Download imaeg

	byte[] jpg = new Jravatar()
		.withSize(50)
		.withRating(GravatarRating.GENERAL_AUDIENCES)
		.withDefaultImage(GravatarDefaultImage.IDENTICON)
		.download("iHaveAn@email.com");