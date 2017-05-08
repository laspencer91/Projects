#include "actionshortcutdisplay.h"

#include <QAction>
#include <QDockWidget>
#include <QToolBar>
#include <QApplication>
#include <QMenu>
#include <QDebug>

actionShortcutDisplay& actionShortcutDisplay::getInstance()
{
    static actionShortcutDisplay instance;
    return instance;
}
actionShortcutDisplay::actionShortcutDisplay() {}

/* Add all of our QActions to a vector */
void actionShortcutDisplay::addAction(QAction* _newAction)
{
    if (!mActionList.contains(_newAction))
        mActionList.append(_newAction);
}

/* Add all our QActions to a vector, actions are added under these conditions:
 * 1: the QAction toolTip is not empty || 2: the QAction is not a seperator */
void actionShortcutDisplay::addActionList(const QVector<QAction*>& listToAdd)
{
    foreach(QAction* _action, listToAdd)
    {
        // Add each action to our ActionList only if it doesnt already exist
        if (!mActionList.contains(_action))
        {
            if (!_action->toolTip().isEmpty() && !_action->isSeparator())
            {
                mActionList.append(_action);
                qDebug() << _action->toolTip() << " ADDED TO LIST";
            }
            else {
                qDebug() << "ACTION THAT WE TRYIED "
                            "TO ADD WAS NOT VALID FOR THIS LIST";
            }
        }
        else {
            qDebug() << _action->text() << "ALREADY EXISTS IN MACTION LIST";
        }
    }
}

/* Add all our QWidgets to a vector */
void actionShortcutDisplay::addDockWidget(QDockWidget* _newWidget)
{
    if (!mWidgetList.contains(_newWidget))
        mWidgetList.append(_newWidget);
}

/* Add an array of QWidgets to our current List */
void actionShortcutDisplay::addDockWidgetList(const QVector<QDockWidget*>& listToAdd)
{
    foreach(QDockWidget* _widget, listToAdd)
    {
        // Add each widget to our action list only if it doesnt already exist
        if (!mWidgetList.contains(_widget)) {
            mWidgetList.append(_widget); // Add this widget to our widget list
            //qDebug() << _widget->objectName() << " ADDED TO LIST";
        }
        else {
            qDebug() << _widget->objectName() << "ALREADY EXISTS IN MWIDGET LIST";
        }
    }
}

/* Run through each DockWidget in our widget list, for each DockWidget we need to
 * grab its toolbar. Then from each toolbar we grab its set of actions. Finally,
 * we add oll of that toolbars actions to our actionList */
void actionShortcutDisplay::populateAListFromWList()
{
    foreach (QDockWidget* _widget, mWidgetList)
    {
        // Find all the childred of type QToolBar of this specific QDockWidget
        QList<QToolBar*> _toolBars = _widget->findChildren<QToolBar*>();

        // Add the actions of each toolBar in our newly create list of QToolbars
        foreach (QToolBar* _aToolBar, _toolBars) {
            addActionList(_aToolBar->actions().toVector());
        }
    }
}

/* Does the specified action already exist in our list? */
bool actionShortcutDisplay::actionFound(QAction* _action) {
    if (mActionList.contains(_action))
        return true;
    else
        return false;
}

void actionShortcutDisplay::setAllActionsShortcutText()
{
    QString _scText; // Shortcut text to append
    QString _ToolText;
    foreach(QAction* actionObj, mActionList)
    {
        _ToolText = actionObj->toolTip(); // Easier repeated reference

        /* If there is not a shortcut assigned to this QAction we erase
           old shortcut text if it exits. */
        if (actionObj->shortcut().isEmpty())
        {
            _scText = QObject::tr("");
            if (_ToolText.contains(QObject::tr("(")))
            {
                /* Erase the old shortcut text starting with */
                actionObj->setToolTip(
                            _ToolText.remove(_ToolText.indexOf(QObject::tr("(")) - 1,
                                              _ToolText.length() - _ToolText.indexOf(QObject::tr("(")) - 1));
            }
        }
        else
        {
            if (!_ToolText.contains(QObject::tr("(")) && !_ToolText.contains(QObject::tr(")"))) {
                _scText = QObject::tr(" (")
                        + actionObj->shortcut().toString() + QObject::tr(")");
            }
        }
        actionObj->setToolTip(actionObj->toolTip() + _scText);
    }
}

/* Update only one specific QAction toolTip */
void actionShortcutDisplay::updateActionShortcutText(QAction* compareObj)
{
    QString _scText; // Shortcut text to append
    QString _ToolText;
    foreach(QAction* actionObj, mActionList)
    {
        if (actionObj == compareObj)
        {
            _ToolText = actionObj->toolTip(); // Easier repeated reference
            /* If there is not a shortcut assigned to this QAction we erase
               old shortcut text if it exits. */
            if (actionObj->shortcut().isEmpty())
            {
                _scText = QObject::tr("");
                if (_ToolText.contains(QObject::tr("(")))
                {
                    /* Erase the old shortcut text starting with */
                    actionObj->setToolTip(
                                _ToolText.remove(_ToolText.indexOf(QObject::tr("(")) - 1,
                                                  _ToolText.length() - _ToolText.indexOf(QObject::tr("(")) - 1));
                }
            }
            else
            {
                if (!_ToolText.contains(QObject::tr("(")) && !_ToolText.contains(QObject::tr(")"))) {
                    _scText = QObject::tr(" (")
                            + actionObj->shortcut().toString() + QObject::tr(")");
                }
            }
            actionObj->setToolTip(actionObj->toolTip() + _scText);
        }
    }
}
