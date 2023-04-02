insert into follows (followed, follower) values (
  :#${body.id},
  :#${exchangeProperty.loggedInUser.id}
)
