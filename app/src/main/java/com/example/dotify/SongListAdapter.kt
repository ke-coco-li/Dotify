package com.example.dotify

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import com.example.dotify.databinding.SongItemBinding

class SongListAdapter(private var songList: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>()  {

    var onSongClickListener: (song: Song) -> Unit = { _ ->  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = SongItemBinding.inflate(LayoutInflater.from(parent.context))
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songList[position]

        with(holder.binding) {
            albumCover.setImageResource(song.smallImageID)
            songTitle.text = song.title
            artistName.text = song.artist
            itemRoot.setOnClickListener {
                onSongClickListener(song)
            }
        }

//        holder.itemView.setOnClickListener { onSongClickListener(song) }
    }

    override fun getItemCount(): Int = songList.size

    fun updateSongs(newSongList: List<Song>) {
        this.songList = newSongList
        notifyDataSetChanged()
    }

    class SongViewHolder(val binding: SongItemBinding): RecyclerView.ViewHolder(binding.root)

}