package com.domain.mariolopez.testlist.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.domain.mariolopez.testlist.ui.ViewAnkoComponent
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlin.properties.Delegates


abstract class BaseAdapter<Item>
    : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<ViewAnkoComponent<RecyclerView>>>() {


    abstract val bind: ViewAnkoComponent<*>.(item: Item) -> Unit

    var items: List<Item> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    private val itemClicksSubject: PublishSubject<Any> = PublishSubject.create<Any>()

    val itemClicks: Observable<Any> = itemClicksSubject.toFlowable(BackpressureStrategy.BUFFER).toObservable()!!

    abstract fun onCreateComponent(parent: RecyclerView, viewType: Int): ViewAnkoComponent<RecyclerView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewAnkoComponent<RecyclerView>> {

        val baseViewHolder = BaseViewHolder(onCreateComponent(parent as RecyclerView, viewType))
        RxView.clicks(baseViewHolder.itemView)
                .takeUntil(RxView.detaches(parent))
                .map<Any> { baseViewHolder.itemView.tag }
                .subscribe { itemClicksSubject.onNext(it) }

        return baseViewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewAnkoComponent<RecyclerView>>, position: Int) {
        val item = items[position]
        holder.itemView.tag = item
        holder.ui.bind(item)

    }

    override fun getItemCount() = items.size

    class BaseViewHolder<out Component : ViewAnkoComponent<RecyclerView>>(val ui: Component)
        : RecyclerView.ViewHolder(ui.inflate())
}