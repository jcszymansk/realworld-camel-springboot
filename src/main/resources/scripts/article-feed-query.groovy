def tag = request.headers.tag
def author = request.headers.author
def liker = request.headers.favorited

// prepare parameters
def params = [:]
if (tag != null) params.tag = tag
if (author != null) params.author = author
if (liker != null) params.liker = liker
request.headers['CamelJpaParameters'] = params

// limit and offset
request.headers['CamelXJpaMaximumResults'] = (request.headers.limit ?: 0) as Integer
request.headers['CamelXJpaFirstResult'] = (request.headers.offset ?: 0) as Integer

// and the query
result = """\
    select a from Article a \
      ${tag == null ? '' : ', Tag t'} \
      ${liker == null ? '' : ', Favorite f'} \
    where \
          1 = 1 \
      ${author == null ? '' : 'and a.author.username = :author'} \
      ${tag == null ? '' : 'and t.article.id = a.id and t.tag = :tag'} \
      ${liker == null ? '' : 'and f.article.id = a.id and f.user.username = :liker'} \
""".trim() // need to trim as Camel will infer update if the query doesn't start with select
