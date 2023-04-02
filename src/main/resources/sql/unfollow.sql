delete from follows where
  follower = :#${exchangeProperty.loggedInUser?.id} and
  followed = :#${body.id}
