import com.github.jacekszymanski.realcamel.entity.User

User user = exchange.properties['loggedInUser']
Map newValues = request.body['user']

newValues.each { if (it.value != null) { user."${it.key}" = it.value } }
