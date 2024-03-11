delete from favorites where
  userid = :#${variable.loggedInUser?.id} and
  article = :#${body.id}
