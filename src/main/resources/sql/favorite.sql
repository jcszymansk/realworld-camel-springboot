insert into favorites (article, userid) values (
  :#${body.id},
  :#${variable.loggedInUser.id}
)
