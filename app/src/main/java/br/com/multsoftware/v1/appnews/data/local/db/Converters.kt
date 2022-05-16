package br.com.multsoftware.v1.appnews.data.local.db

import androidx.room.TypeConverter
import br.com.multsoftware.v1.appnews.data.local.model.Source

/**
 * Created by João Bosco on 07/06/2021.
 * e-mail - Support: ti.junior@gmail.com
 */

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}