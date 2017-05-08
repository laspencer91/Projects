#include <QAction>
#include <QVector>
#include <QDockWidget>
#include <QToolBar>

#ifndef ACTIONSHORTCUTDISPLAY_H
#define ACTIONSHORTCUTDISPLAY_H

class actionShortcutDisplay
{
    public:
        static actionShortcutDisplay& getInstance();

        /* Add a QActions to our vector */
        void addAction(QAction* _newAction);
        void addActionList(const QVector<QAction*>& listToAdd);
        void setAllActionsShortcutText();
        void updateActionShortcutText(QAction* compareObj);
        void addDockWidgetList(const QVector<QDockWidget*>& listToAdd);
        void addDockWidget(QDockWidget* _newWidget);
        void populateAListFromWList();
        bool actionFound(QAction* _action);

        /* Do not want these methods for Singleton class, ensure only
           one instance ever exists */
        actionShortcutDisplay(actionShortcutDisplay const&) = delete;
        void operator=(actionShortcutDisplay const&) = delete;

    private:
        // This vector houses all of the created actions in our program
        QVector<QAction*> mActionList;
        QVector<QDockWidget*> mWidgetList;

        actionShortcutDisplay();

};

#endif // ACTIONSHORTCUTDISPLAY_H
