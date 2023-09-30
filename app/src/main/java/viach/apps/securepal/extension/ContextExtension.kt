package viach.apps.securepal.extension

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openLink(url: String) = startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))