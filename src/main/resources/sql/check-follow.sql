select count(*) from follows where
  follower = :#${variable.loggedInUser?.id} and
  followed = :#${variable.checkFollow.id}
