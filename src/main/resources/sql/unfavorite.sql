delete from favorites where
  userid = :#${exchangeProperty.loggedInUser?.id} and
  article = :#${body.id}
