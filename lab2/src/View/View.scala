package View
import List.{LinkedListFactory, _}
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import javax.swing._
import objects._



class View(factory:LinkedListFactory) extends JFrame {
  this.setSize(800, 400)
  this.getContentPane.setLayout(null)
  var jLabel: JLabel = null
  var jTextField: JTextField = null
  var jButtonAdd: JButton = null
  var jButtonDelete: JButton = null
  var jButtonSort: JButton = null
  var jButtonSave: JButton = null
  var jButtonLoad: JButton = null
  var jComboBox: JComboBox[_] = null
  var linklist: LinkedList[IUserType] = null
  var panel = new JScrollPane(getJLabel)
  panel.setBounds(34, 49, 300, 50)
  this.add(panel)
  this.add(getJButtonAdd, null)
  this.add(getJButtonDelete, null)
  this.add(getJButtonSort, null)
  this.add(getJButtonSave, null)
  this.add(getJButtonLoad, null)
  this.add(getJComboBox, null)
  this.setTitle("LinkedList")
  val executor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor


  private def getJLabel = {
    if (jLabel == null) {
      jLabel = new JLabel
      jLabel.setBounds(40, 50, 300, 18)
      jLabel.setText(" ")
    }
    jLabel
  }

  private def getJTextField = {
    if (jTextField == null) {
      jTextField = new JTextField
      jTextField.setBounds(40, 100, 160, 20)
    }
    jTextField
  }

  private def getJButtonAdd = {
    if (jButtonAdd == null) {
      jButtonAdd = new JButton
      jButtonAdd.setBounds(600, 100, 80, 30)
      jButtonAdd.setText("Add")
    }
    jButtonAdd.addActionListener(new ActionListener() {
      override def actionPerformed(e: ActionEvent): Unit = {
        linklist.add(linklist.getType.create)
        jLabel.setText(linklist.printList)
      }
    })
    jButtonAdd
  }

  private def getJButtonDelete = {
    if (jButtonDelete == null) {
      jButtonDelete = new JButton
      jButtonDelete.setBounds(600, 150, 80, 30)
      jButtonDelete.setText("Delete")
    }
    jButtonDelete.addActionListener(new ActionListener() {
      override def actionPerformed(e: ActionEvent): Unit = {
        linklist.remove
        jLabel.setText(linklist.printList)
      }
    })
    jButtonDelete
  }

  private def getJButtonSort = {
    if (jButtonSort == null) {
      jButtonSort = new JButton
      jButtonSort.setBounds(600, 200, 80, 30)
      jButtonSort.setText("Sort")
    }
    jButtonSort.addActionListener(new ActionListener() {
      override def actionPerformed(e: ActionEvent): Unit = {
        linklist.sort
        jLabel.setText(linklist.printList)
      }
    })
    jButtonSort
  }

  private def getJButtonSave = {
    if (jButtonSave == null) {
      jButtonSave = new JButton
      jButtonSave.setBounds(600, 250, 80, 30)
      jButtonSave.setText("Save")
    }
    jButtonSave.addActionListener(new ActionListener() {
      override def actionPerformed(e: ActionEvent): Unit = { //  list.save();
        jLabel.setText(linklist.printList)
      }
    })
    jButtonSave
  }

  private def getJButtonLoad = {
    if (jButtonLoad == null) {
      jButtonLoad = new JButton
      jButtonLoad.setBounds(600, 300, 80, 30)
      jButtonLoad.setText("Load")
    }
    jButtonLoad.addActionListener(new ActionListener() {
      override def actionPerformed(e: ActionEvent): Unit = {
        linklist.load
        jLabel.setText(linklist.printList)
      }
    })
    jButtonLoad
  }

  private def getJComboBox = {
    val jComboBox = new JComboBox(factory.getList)
    jComboBox.setEditable(true)
    jComboBox.setBounds(35, 150, 300, 40)
    jComboBox.addActionListener(new ActionListener() {
      override def actionPerformed(e: ActionEvent): Unit = {
        val combo = e.getSource.asInstanceOf[JComboBox[_]]
        val currentQuantity = combo.getSelectedItem.asInstanceOf[String]
        linklist = factory.getBuilderByName(currentQuantity)
      }
    })
    jComboBox
  }
}
