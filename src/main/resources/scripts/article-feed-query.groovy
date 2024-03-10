{ tag, author, liker ->

// prepare parameters
  def params = [:]
  if (tag != null) params.tag = tag
  if (author != null) params.author = author
  if (liker != null) params.liker = liker
  request.headers['CamelJpaParameters'] = params

// and the query
  """\
  from Article a \
    ${tag == null ? '' : ', Tag t'} \
    ${liker == null ? '' : ', Favorite f'} \
  where \
        1 = 1 \
    ${author == null ? '' : 'and a.author.username = :author'} \
    ${tag == null ? '' : 'and t.article.id = a.id and t.tag = :tag'} \
    ${liker == null ? '' : 'and f.article.id = a.id and f.user.username = :liker'} \
"""
}(request.headers.tag, request.headers.author, request.headers.favorited)
