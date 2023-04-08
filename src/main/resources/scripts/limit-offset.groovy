// limit and offset
request.headers['CamelXJpaMaximumResults'] = (request.headers.limit ?: 0) as Integer
request.headers['CamelXJpaFirstResult'] = (request.headers.offset ?: 0) as Integer
