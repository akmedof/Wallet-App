package com.aslan.wallet.presentation.screen.analytics

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aslan.wallet.R
import com.aslan.wallet.databinding.FragmentAnalyticsBinding
import com.aslan.wallet.presentation.adapter.CategoryAdapter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class AnalyticsFragment : Fragment() {

    private var adapter = CategoryAdapter(arrayListOf())
    private lateinit var viewModel: AnalyticsViewModel

    private var entries = arrayListOf<PieEntry>()

    private var _binding: FragmentAnalyticsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[AnalyticsViewModel::class.java]
        viewModel.loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnalyticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //        val pie = AnyChart.pie()
//        pie.setOnClickListener(object : ListenersInterface.OnClickListener(arrayOf("x", "value")) {
//            override fun onClick(event: Event) {
//                Toast.makeText(
//                    requireContext(),
//                    event.getData().get("x").toString() + ":" + event.getData().get("value"),
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        })
//        val data: MutableList<DataEntry> = ArrayList()
//        viewModel.categories.observe(viewLifecycleOwner){ categories ->
//            categories?.let {
//                adapter.updateCategories(it)
//                data.addAll(it.map { c -> ValueDataEntry(c.name, c.amount) })
//                pie.data(data)
//                pie.labels().position("outside")
//                pie.legend()
//                    .position("center-bottom")
//                    .itemsLayout(LegendLayout.HORIZONTAL)
//                    .align(Align.CENTER)
//            }
//        }
//        binding.anyChartView.setChart(pie)

        getData()

    }

    private fun getData(){
        setupPieChart()

        binding.categoryRTList.layoutManager = LinearLayoutManager(requireContext())
        binding.categoryRTList.adapter = adapter

        viewModel.categories.observe(viewLifecycleOwner){ categories ->
            categories?.let {
                adapter.updateCategories(it)
                entries.clear()
                entries.addAll(it.map { c -> PieEntry(c.amount!!.toFloat(), c.name) })
                loadPieChartData()
            }
        }

    }

    private fun setupPieChart() {
        binding.pieChart.isDrawHoleEnabled = true
        binding.pieChart.setUsePercentValues(true)
        binding.pieChart.setEntryLabelTextSize(12f)
        binding.pieChart.setEntryLabelColor(Color.BLACK)
        binding.pieChart.isEnabled = false
        binding.pieChart.centerText = "Wallet"
        binding.pieChart.setCenterTextSize(24f)
        binding.pieChart.description.isEnabled = false
        val l = binding.pieChart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
//                l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(true)
        l.isEnabled = true

    }

    private fun loadPieChartData() {
        val colors = ArrayList<Int>()
        for (color in ColorTemplate.MATERIAL_COLORS) {
            colors.add(color)
        }
        for (color in ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color)
        }
        val dataSet = PieDataSet(entries, "")
        dataSet.colors = colors
        val data = PieData(dataSet)
        data.setDrawValues(true)
        data.setValueFormatter(PercentFormatter(binding.pieChart))
        data.setValueTextSize(15f)
        data.setValueTextColor(Color.WHITE)
        binding.pieChart.data = data
        binding.pieChart.invalidate()
        binding.pieChart.animateY(1400, Easing.EaseInOutQuad)

    }


}