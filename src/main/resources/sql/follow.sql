insert into follows (followed, follower) values (
  :#${body.id},
  :#${variable.loggedInUser.id}
)
