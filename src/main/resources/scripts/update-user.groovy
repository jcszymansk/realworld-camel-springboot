import com.github.jacekszymanski.realcamel.entity.User
import org.apache.commons.codec.digest.DigestUtils

User user = exchange.properties['loggedInUser']
Map newValues = request.body['user']

if (newValues.password) {
    newValues.password = DigestUtils.sha256Hex(newValues.password)
}

newValues.each { if (it.value != null) { user."${it.key}" = it.value } }
