package View

import List.IUserType
import List.LinkedList
import List.LinkedListFactory
import java.util.concurrent.Executors
import javax.swing.*


class View : JFrame {
    private var jLabel: JLabel? = null
    private var jTextField: JTextField? = null
    private var jButtonAdd: JButton? = null
    private var jButtonDelete: JButton? = null
    private var jButtonSort: JButton? = null
    private var jButtonSave: JButton? = null
    private var jButtonLoad: JButton? = null
    private var jComboBox: JComboBox<*>? = null

    var factory = LinkedListFactory()
    var list: LinkedList<IUserType?>? = null

    constructor() {
        this.setSize(800, 400)
        this.contentPane.layout = null
        val panel = JScrollPane(getJLabel())
        panel.setBounds(34, 49, 300, 50)
        this.add(panel)
        this.add(getJButtonAdd(), null)
        this.add(getJButtonDelete(), null)
        this.add(getJButtonSort(), null)
        this.add(getJButtonSave(), null)
        this.add(getJButtonLoad(), null)
        this.add(getJComboBox(), null)
        title = "LinkedList"
        val executor = Executors.newSingleThreadScheduledExecutor()
    }

    private fun getJLabel(): JLabel {
        if (jLabel == null) {
            jLabel = JLabel()
            jLabel!!.setBounds(40, 50, 300, 18)
            jLabel!!.text = " "
        }
        return jLabel!!
    }

    private fun getJTextField(): JTextField {
        if (jTextField == null) {
            jTextField = JTextField()
            jTextField!!.setBounds(40, 100, 160, 20)
        }
        return jTextField!!
    }

    private fun getJButtonAdd(): JButton {
        if (jButtonAdd == null) {
            jButtonAdd = JButton()
            jButtonAdd!!.setBounds(600, 100, 80, 30)
            jButtonAdd!!.text = "Add"
        }
        jButtonAdd!!.addActionListener {
            list!!.add(list!!.getType()!!.create()!!)
            jLabel!!.text = list!!.printList()
        }
        return jButtonAdd as JButton
    }

    private fun getJButtonDelete(): JButton {
        if (jButtonDelete == null) {
            jButtonDelete = JButton()
            jButtonDelete!!.setBounds(600, 150, 80, 30)
            jButtonDelete!!.text = "Delete"
        }
        jButtonDelete!!.addActionListener {
            list!!.remove()
            jLabel!!.text = list!!.printList()
        }
        return jButtonDelete as JButton
    }

    private fun getJButtonSort(): JButton {
        if (jButtonSort == null) {
            jButtonSort = JButton()
            jButtonSort!!.setBounds(600, 200, 80, 30)
            jButtonSort!!.text = "Sort"
        }
        jButtonSort!!.addActionListener {
            list!!.sort()
            jLabel!!.text = list!!.printList()
        }
        return jButtonSort as JButton
    }

    private fun getJButtonSave(): JButton {
        if (jButtonSave == null) {
            jButtonSave = JButton()
            jButtonSave!!.setBounds(600, 250, 80, 30)
            jButtonSave!!.text = "Save"
        }
        jButtonSave!!.addActionListener { //  list.save();
            jLabel!!.text = list!!.printList()
        }
        return jButtonSave as JButton
    }

    private fun getJButtonLoad(): JButton {
        if (jButtonLoad == null) {
            jButtonLoad = JButton()
            jButtonLoad!!.setBounds(600, 300, 80, 30)
            jButtonLoad!!.text = "Load"
        }
        jButtonLoad!!.addActionListener {
            list!!.load()
            jLabel!!.text = list!!.printList()
        }
        return jButtonLoad as JButton
    }

    private fun getJComboBox(): JComboBox<*> {
        val jComboBox: JComboBox<*> = JComboBox<Any?>(factory.getList())
        jComboBox.isEditable = true
        jComboBox.setBounds(35, 150, 300, 40)
        jComboBox.addActionListener { e ->
            val combo = e.source as JComboBox<*>
            val currentQuantity = combo.selectedItem as String
            list = factory.getBuilderByName(currentQuantity)
        }
        return jComboBox
    }
}