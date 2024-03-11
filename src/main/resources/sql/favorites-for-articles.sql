select
  a.id as article,
  (select count(*) from favorites f where f.article = a.id) as favorites,
  (select count(*) from favorites fm
      where fm.article = a.id and fm.userid = :#${variable.loggedInUser?.id})
  as myfavorite
from
  articles a
where
  a.id in (:#in:${variable.articleIds})
group by
  a.id
