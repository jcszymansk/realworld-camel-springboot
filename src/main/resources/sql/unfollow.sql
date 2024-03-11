delete from follows where
  follower = :#${variable.loggedInUser?.id} and
  followed = :#${body.id}
