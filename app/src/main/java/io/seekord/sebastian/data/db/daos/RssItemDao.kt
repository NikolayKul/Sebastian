package io.seekord.sebastian.data.db.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import io.seekord.sebastian.data.db.models.RssItemEntity

@Dao
interface RssItemDao {

    @Insert(onConflict = REPLACE)
    fun insertOrUpdate(item: RssItemEntity)

    @Insert(onConflict = REPLACE)
    fun insertOrUpdate(items: List<RssItemEntity>)


}