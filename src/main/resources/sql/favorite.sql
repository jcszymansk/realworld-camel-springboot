insert into favorites (article, userid) values (
  :#${body.id},
  :#${exchangeProperty.loggedInUser.id}
)
